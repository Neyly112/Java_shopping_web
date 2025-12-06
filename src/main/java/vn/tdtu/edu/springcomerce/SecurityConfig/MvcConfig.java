package vn.tdtu.edu.springcomerce.SecurityConfig;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Đảm bảo các file tĩnh trong static/images có thể được truy cập
        exposeDirectory("images", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        // Đường dẫn đầy đủ tới thư mục static/images
        Path uploadDir = Paths.get("src/main/resources/static/" + dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // Đảm bảo không có ".." trong đường dẫn
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        // Cấu hình Spring để phục vụ các tài nguyên tĩnh từ thư mục static/images
        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}
