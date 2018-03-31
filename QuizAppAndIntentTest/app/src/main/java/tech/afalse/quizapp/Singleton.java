package tech.afalse.quizapp;

import java.util.ArrayList;

/**
 * Created by aqali on 31.03.2018.
 */

class Singleton {
    ArrayList<Question> questions;

    private static final Singleton ourInstance = new Singleton();

    static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        questions = new ArrayList<>();
        questions.add(new Question("Is Astana the capital of Kazakhstan?", true));
        questions.add(new Question("Is Moscow the capital of Russian Federation?", true));
        questions.add(new Question("Is Moscow the capital of Kazakhstan?", false));
    }
}
