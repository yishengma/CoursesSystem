package piratehat.coursessystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import piratehat.coursessystem.R;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class InfoAdapter extends RecyclerView.Adapter {


    private List mList;
    private Context mContext;
    private int mType;


    private OnClickListener mListener;

    public void setListener(OnClickListener listener) {
        mListener = listener;
    }

    public interface OnClickListener {
        void OnClick(Object o,int position);

        void OnLongClick(Object o,int position);
    }

    public InfoAdapter(List list, Context context, int type) {
        mList = list;
        mContext = context;
        mType = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (mType == Constant.STUDENT_TYPE) {
            return new StudentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_student_info, parent, false));
        }

        if (mType == Constant.TEACHER_TYPE) {
            return new TeacherViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_teacher_info, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof StudentViewHolder) {
            final Student student = (Student) mList.get(position);
            ((StudentViewHolder) holder).mTvNo.setText(String.valueOf(student.getNo()));
            ((StudentViewHolder) holder).mTvAge.setText(String.valueOf(student.getAge()));
            ((StudentViewHolder) holder).mTvName.setText(student.getName());
            ((StudentViewHolder) holder).mTvSex.setText(student.getSex());
            ((StudentViewHolder) holder).mTvSchool.setText(student.getSchool());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null && Constant.STUDENT_TYPE == mType) {
                        mListener.OnClick(student,position);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mListener != null && Constant.STUDENT_TYPE == mType) {
                        mListener.OnLongClick(student,position);
                    }
                    return false;
                }
            });

        }

        if (holder instanceof TeacherViewHolder) {
            final Teacher teacher = (Teacher) mList.get(position);
            ((TeacherViewHolder) holder).mTvNo.setText(String.valueOf(teacher.getNo()));
            ((TeacherViewHolder) holder).mTvName.setText(teacher.getName());
            ((TeacherViewHolder) holder).mTvSex.setText(teacher.getSex());
            ((TeacherViewHolder) holder).mTvTel.setText(teacher.getTel());
            ((TeacherViewHolder) holder).mTvSchool.setText(teacher.getSchool());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null && Constant.TEACHER_TYPE == mType) {
                        mListener.OnClick(teacher,position);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mListener != null && Constant.TEACHER_TYPE == mType) {
                        mListener.OnLongClick(teacher,position);
                    }
                    return false;
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_no)
        TextView mTvNo;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_school)
        TextView mTvSchool;
        @BindView(R.id.tv_age)
        TextView mTvAge;
        @BindView(R.id.tv_sex)
        TextView mTvSex;

        StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class TeacherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_no)
        TextView mTvNo;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_school)
        TextView mTvSchool;
        @BindView(R.id.tv_tel)
        TextView mTvTel;
        @BindView(R.id.tv_sex)
        TextView mTvSex;

        TeacherViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
