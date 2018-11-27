package piratehat.coursessystem.bean;

/**
 *
 * Created by PirateHat on 2018/11/27.
 */
import java.util.List;

public class PostList<T> {
    private String mType;
    private List<T> mList;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }
}
