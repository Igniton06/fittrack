package com.fittrack;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class FitnessController {
    private final List<Workout> workouts = new CopyOnWriteArrayList<>();
    private final AtomicInteger waterGlasses = new AtomicInteger(0);

    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return workouts;
    }

    @PostMapping("/workouts")
    public Workout addWorkout(@RequestBody Workout workout) {
        if (workout.name() == null || workout.name().isBlank()) {
            throw new IllegalArgumentException("Exercise name is required.");
        }
        if (workout.minutes() < 1 || workout.minutes() > 600) {
            throw new IllegalArgumentException("Duration must be between 1 and 600 minutes.");
        }
        if (workout.calories() < 0 || workout.calories() > 10000) {
            throw new IllegalArgumentException("Calories must be between 0 and 10000.");
        }
        Workout saved = new Workout(
                UUID.randomUUID().toString(),
                workout.name().trim(),
                workout.minutes(),
                workout.calories(),
                java.time.LocalDate.now().toString()
        );
        workouts.add(0, saved);
        return saved;
    }

    @DeleteMapping("/workouts/{id}")
    public Map<String, String> deleteWorkout(@PathVariable String id) {
        boolean removed = workouts.removeIf(w -> w.id().equals(id));
        if (!removed) {
            throw new NoSuchElementException("Workout not found.");
        }
        return Map.of("message", "Workout deleted.");
    }

    @GetMapping("/water")
    public Map<String, Integer> getWater() {
        return Map.of("glasses", waterGlasses.get(), "goal", 8);
    }

    @PostMapping("/water")
    public Map<String, Integer> addWater() {
        return Map.of("glasses", waterGlasses.updateAndGet(n -> Math.min(n + 1, 30)), "goal", 8);
    }

    @DeleteMapping("/water")
    public Map<String, Integer> resetWater() {
        waterGlasses.set(0);
        return getWater();
    }

    @PostMapping("/bmi")
    public BmiResult calculateBmi(@RequestBody BmiRequest request) {
        if (request.heightCm() < 50 || request.heightCm() > 250 ||
            request.weightKg() < 10 || request.weightKg() > 500) {
            throw new IllegalArgumentException("Enter a valid height and weight.");
        }
        double heightM = request.heightCm() / 100.0;
        double bmi = request.weightKg() / (heightM * heightM);
        String category;
        if (bmi < 18.5) category = "Underweight";
        else if (bmi < 25) category = "Healthy range";
        else if (bmi < 30) category = "Overweight";
        else category = "Obesity range";
        return new BmiResult(Math.round(bmi * 10.0) / 10.0, category);
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        int totalMinutes = workouts.stream().mapToInt(Workout::minutes).sum();
        int totalCalories = workouts.stream().mapToInt(Workout::calories).sum();
        return Map.of(
                "workouts", workouts.size(),
                "minutes", totalMinutes,
                "calories", totalCalories,
                "waterGlasses", waterGlasses.get()
        );
    }

    public record Workout(String id, String name, int minutes, int calories, String date) {
        public Workout(String name, int minutes, int calories) {
            this(null, name, minutes, calories, null);
        }
    }
    public record BmiRequest(double heightCm, double weightKg) {}
    public record BmiResult(double bmi, String category) {}
}
