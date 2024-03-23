package com.example.a111221218_hw1;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "password";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 获取布局中的EditText和TextView
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        txvShow = findViewById(R.id.txvShow);
    }

    // XML中android:onClick属性指定的点击事件处理方法
    public void loginButtonClicked(View view) {
        try {
            // 获取用户输入的用户名和密码
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // 检查用户名和密码是否匹配
            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                // 登录成功，显示成功消息
                txvShow.setText("登入成功");
            } else {
                // 登录失败，显示错误消息
                txvShow.setText("使用者名稱或密碼錯誤");
            }
        } catch (Exception e) {
            // 记录异常信息到日志中
            Log.e("LoginActivity", "發生異常: " + e.getMessage(), e);
            // 显示一个友好的错误消息给用户
            Toast.makeText(this, "發生異常，請稍後重試", Toast.LENGTH_SHORT).show();
        }
    }
}
