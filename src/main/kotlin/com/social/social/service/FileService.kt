package com.social.social.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.util.*

@Service
class FileService {

        fun uploadFile(file: MultipartFile): String {

            val today = LocalDate.now()
            val baseUploadDir = "uploads/"
            val uploadDir = "${baseUploadDir}${today.year}/${today.monthValue}/${today.dayOfMonth}/"

            Files.createDirectories(Paths.get(uploadDir))

            val fileName = UUID.randomUUID().toString() + "_" + file.originalFilename
            val filePath = Paths.get(uploadDir + fileName)

            Files.write(filePath, file.bytes)

            return filePath.toString()
        }
}