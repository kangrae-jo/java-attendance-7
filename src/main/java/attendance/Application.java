package attendance;


import attendance.config.AppConfig;
import attendance.controller.Controller;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        try {
            AppConfig appConfig = new AppConfig();
            Controller controller = appConfig.controller();
            controller.run();
        } finally {
            Console.close();
        }
    }

}
