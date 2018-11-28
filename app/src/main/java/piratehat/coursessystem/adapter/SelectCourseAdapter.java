package piratehat.coursessystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import piratehat.coursessystem.R;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.constant.Constant;

/**
 *
 * Created by PirateHat on 2018/11/28.
 */

public class SelectCourseAdapter extends RecyclerView.Adapter {


    private int mType;
    private List<Course> mCourses;
    private Context mContext;

    private OnClickListener mListener;


    public void setListener(OnClickListener listener) {
        mListener = listener;
    }

    public interface OnClickListener {
        void OnClick(Course course);
    }

    public SelectCourseAdapter(List<Course> courses, Context context, int type) {
        mCourses = courses;

        mContext = context;
        mType = type;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mType == Constant.SELECTED) {
            return new SelectCourseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_selected_course, parent, false));

        }
        if (mType == Constant.UNSELECTED) {
            return new UnselectedCourseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_unselect_course, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Course course = mCourses.get(position);
        if (holder instanceof SelectCourseViewHolder) {
            ((SelectCourseViewHolder) holder).mTvName.setText(course.getName());
            ((SelectCourseViewHolder) holder).mTvSchool.setText(course.getSchool());
            ((SelectCourseViewHolder) holder).mTvTel.setText(course.getTeacher().getTel());
            ((SelectCourseViewHolder) holder).mTvTeacher.setText(course.getTeacher().getName());
            ((SelectCourseViewHolder) holder).mTvScore.setText(course.getScore()==0 ? "暂无" : String.valueOf(course.getScore()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.OnClick(course);
                    }
                }
            });

        }

        if (holder instanceof UnselectedCourseViewHolder) {
            ((UnselectedCourseViewHolder) holder).mTvName.setText(course.getName());
            ((UnselectedCourseViewHolder) holder).mTvSchool.setText(course.getSchool());
            ((UnselectedCourseViewHolder) holder).mTvTel.setText(course.getTeacher().getTel());
            ((UnselectedCourseViewHolder) holder).mTvTeacher.setText(course.getTeacher().getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.OnClick(course);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    class SelectCourseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_teacher)
        TextView mTvTeacher;
        @BindView(R.id.tv_score)
        TextView mTvScore;
        @BindView(R.id.tv_tel)
        TextView mTvTel;
        @BindView(R.id.tv_school)
        TextView mTvSchool;

        SelectCourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class UnselectedCourseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_teacher)
        TextView mTvTeacher;
        @BindView(R.id.tv_tel)
        TextView mTvTel;
        @BindView(R.id.tv_school)
        TextView mTvSchool;

        UnselectedCourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
