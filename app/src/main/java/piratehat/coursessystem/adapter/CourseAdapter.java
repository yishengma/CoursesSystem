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
import piratehat.coursessystem.bean.Course;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class CourseAdapter extends RecyclerView.Adapter {

    private List<Course> mCourse1s;
    private Context mContext;

    private OnClickListener mListener;


    public void setListener(OnClickListener listener) {
        mListener = listener;
    }

    public interface OnClickListener {
        void OnClick(Course course);
    }

    public CourseAdapter(List<Course> course1s, Context context) {
        mCourse1s = course1s;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Course course_1 = mCourse1s.get(position);
            ((ViewHolder) holder).mTvNo.setText(String.valueOf(course_1.getNo()));
            ((ViewHolder) holder).mTvName.setText(course_1.getName());
            ((ViewHolder) holder).mTvSchool.setText(course_1.getSchool());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.OnClick(course_1);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCourse1s.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_no)
        TextView mTvNo;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_school)
        TextView mTvSchool;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
