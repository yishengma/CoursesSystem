package piratehat.coursessystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import piratehat.coursessystem.R;
import piratehat.coursessystem.bean.Student;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class ScoreAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<Student> mStudents;

    public ScoreAdapter(Context context, List<Student> students) {
        mContext = context;
        mStudents = students;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_student_score, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            final Student student = mStudents.get(position);
            ((ViewHolder) holder).mTvNo.setText(String.valueOf(student.getNo()));
            ((ViewHolder) holder).mTvName.setText(student.getName());
            ((ViewHolder) holder).mEtScore.setText(String.valueOf(student.getScore()));
            ((ViewHolder) holder).mEtScore.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String s1 = ((ViewHolder) holder).mEtScore.getText().toString();
                    if (TextUtils.isEmpty(s1)){
                        s1 = "0";
                    }
                    student.setScore(Integer.valueOf(s1));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_no)
        TextView mTvNo;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_score)
        TextView mTvScore;
        @BindView(R.id.et_score)
        EditText mEtScore;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
