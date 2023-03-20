package com.bridgelabz.test;

import com.bridgelabz.keywordengine.Keyword_Engine;
import org.testng.annotations.Test;

public class Login_Test {
    public Keyword_Engine Keyword;

    @Test
    public void login_Test() {
        Keyword = new Keyword_Engine();
        Keyword.start_Execution("Login");
    }
}
