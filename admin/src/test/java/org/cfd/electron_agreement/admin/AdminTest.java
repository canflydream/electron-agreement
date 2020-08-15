package org.cfd.electron_agreement.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AdminTest {
    @Test
    public void test(){
        String str = "{{aaa}},{{ddd}},{{ccc}},{{ttt}}";
        String pattern = "\\{\\{([^}]*)\\}\\}";

        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(str);

        StringBuilder sql = new StringBuilder();
        while(matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            sql.append(matcher.group(1)+",");
        }
        if (sql.length() > 0) {
            sql.deleteCharAt(sql.length() - 1);
        }

    }
}
