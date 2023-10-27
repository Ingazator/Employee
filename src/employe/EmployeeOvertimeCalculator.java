package employe;

import java.time.Duration;
import java.time.LocalTime;

public class EmployeeOvertimeCalculator {
    private static final int WEEKLY_THRESHOLD_HOURS = 40;

    public static void main(String[] args) {
        LocalTime mondayStartTime = LocalTime.of(8, 0);
        LocalTime mondayEndTime = LocalTime.of(17, 0);
        LocalTime tuesdayStartTime = LocalTime.of(8, 0);
        LocalTime tuesdayEndTime = LocalTime.of(17, 0);
        LocalTime wednesdayStartTime = LocalTime.of(8, 0);
        LocalTime wednesdayEndTime = LocalTime.of(17, 0);
        LocalTime thursdayStartTime = LocalTime.of(8, 0);
        LocalTime thursdayEndTime = LocalTime.of(17, 0);
        LocalTime fridayStartTime = LocalTime.of(8, 0);
        LocalTime fridayEndTime = LocalTime.of(18, 30);

        Duration regularHours = calculateRegularHours(mondayStartTime, mondayEndTime)
                .plus(calculateRegularHours(tuesdayStartTime, tuesdayEndTime))
                .plus(calculateRegularHours(wednesdayStartTime, wednesdayEndTime))
                .plus(calculateRegularHours(thursdayStartTime, thursdayEndTime))
                .plus(calculateRegularHours(fridayStartTime, fridayEndTime));
        Duration overtimeHours = calculateOvertimeHours(regularHours);

        System.out.println("Regular hours: " + regularHours.toHours() + " hours");
        System.out.println("Overtime hours: " + overtimeHours.toHours() + " hours");
    }

    private static Duration calculateRegularHours(LocalTime startTime, LocalTime endTime) {
        return Duration.between(startTime, endTime);
    }

    private static Duration calculateOvertimeHours(Duration regularHours) {
        Duration weeklyThreshold = Duration.ofHours(WEEKLY_THRESHOLD_HOURS);
        return regularHours.minus(weeklyThreshold).abs();
    }
}
