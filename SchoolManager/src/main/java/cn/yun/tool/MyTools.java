package cn.yun.tool;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class MyTools {

    //照片下载工具
    public String uploadImg(String phone, MultipartFile img) throws IOException {
        File file = new File("D:/IDEA/WorkSpace/Spring Boot/SchoolManager/src/main/resources/static/picture/"+phone);
        if (!file.exists()){
            file.mkdir();
        }
        String newImgName = UUID.randomUUID()+img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
        File file1 = new File(file +"/"+  newImgName);
        img.transferTo(file1);
        String oldPath = file1.getAbsolutePath().substring(file1.getAbsolutePath().lastIndexOf("\\static"));
        String path = oldPath.replace('\\', '/');
        return path;
    }


}
