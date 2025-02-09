import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TimeWeatherThread extends Thread {
    private boolean isRunning = true;
    private String currentWeather = "맑음";
    private final String[] weatherTypes = {"맑음", "흐림", "비", "눈", "안개"};
    private final Random random = new Random();

    @Override
    public void run() {
        while (isRunning) {
            try {
                // 현재 시간 가져오기
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedTime = now.format(formatter);

                // 랜덤하게 날씨 변경 (20% 확률)
                if (random.nextInt(5) == 0) {
                    currentWeather = weatherTypes[random.nextInt(weatherTypes.length)];
                }

                // 현재 시간과 날씨 출력
                System.out.println("\n----------------------------------------");
                System.out.println("현재 시간: " + formattedTime);
                System.out.println("현재 날씨: " + currentWeather);
                System.out.println("----------------------------------------");

                // 30초마다 업데이트
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                System.out.println("시간/날씨 스레드가 중단되었습니다.");
                break;
            }
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}