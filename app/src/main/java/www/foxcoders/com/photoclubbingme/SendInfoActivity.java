package www.foxcoders.com.photoclubbingme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SendInfoActivity extends AppCompatActivity implements View.OnClickListener {

    Switch aSwitch;
    EditText editText,editText1;
    Button send;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_info);

        toolbar=(Toolbar)findViewById(R.id.infoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText=(EditText)findViewById(R.id.etAddress);
        editText1=(EditText)findViewById(R.id.etDOB);
        aSwitch=(Switch)findViewById(R.id.aSwitch);
        send=(Button)findViewById(R.id.btnSend);
        send.setOnClickListener(this);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    editText.setVisibility(View.VISIBLE);
                    editText1.setVisibility(View.VISIBLE);
                    aSwitch.setText("less info");
                }
                else {
                    editText.setVisibility(View.GONE);
                    editText1.setVisibility(View.GONE);
                    aSwitch.setText("more detail");

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        //// TODO: 9/15/2017
    }
}
