import io.renren.AdminApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <p></p>
 *
 * @author 松竹
 * @version $$ Id: SpringBootStartApplication.java, V 0.1 2020-06-02 17:58 wanggengen Exp $$
 **/
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(AdminApplication.class);
    }

}
