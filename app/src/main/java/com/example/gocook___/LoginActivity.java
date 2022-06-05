package com.example.gocook___;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;         // 파이어베이스 인증
    private DatabaseReference mDatabaseRef;     // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;          // 로그인 입력필드

    private TextView btn_register;              // 회원가입 버튼
    private ImageView icon_register;            // 회원가입 아이콘

    private TextView btn_mlogin;                // 관리자 로그인 버튼
    private ImageView icon_mlogin;              // 관리자 로그인 아이콘

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("GoCook");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);


        Button btn_login = findViewById(R.id.btn_login); // 로그인 버튼
        btn_login.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                
                // 로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                
                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공!
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        TextView btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {

                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ImageView icon_register = findViewById(R.id.icon_register);
        icon_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        TextView btn_mlogin = findViewById(R.id.btn_mlogin);
        btn_mlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 관리자 로그인 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, ManagerLoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView icon_mlogin = findViewById(R.id.icon_mlogin);
        btn_mlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 관리자 로그인 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, ManagerLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}