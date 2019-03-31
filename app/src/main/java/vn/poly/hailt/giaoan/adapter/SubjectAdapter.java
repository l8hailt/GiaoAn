package vn.poly.hailt.giaoan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.poly.hailt.giaoan.R;
import vn.poly.hailt.giaoan.model.Subject;

// 2. SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder>
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    // 4. Khai báo 2 biến Context và List
    private Context context;
    private List<Subject> subjects;

    // 6.1. Khai báo
    private OnItemClickActionListener listener;

    // 5. Tạo constructor: Alt + Insert -> Constructor
    public SubjectAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    // 6. Tạo interface
    public interface OnItemClickActionListener {
        void onItem(int position);
        void onEdit(int position);
        void onDelete(int position);
    }

    // 6.2. Khởi tạo
    public void setOnItemClickActionListener(OnItemClickActionListener listener) {
        this.listener = listener;
    }

    // 3. Alt + Enter (Trỏ vào dòng báo đỏ) -> Implement methods
    @NonNull
    @Override
    // SDK cũ hơn: viewGroup <=> parent, i <=> viewType
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_subject, viewGroup, false);
        return new SubjectHolder(v);
    }

    @Override
    // SDK cũ hơn: subjectHolder <=> holder, i <=> position
    public void onBindViewHolder(@NonNull final SubjectHolder subjectHolder, int i) {
        final Subject subject = subjects.get(i);

        subjectHolder.tvID.setText(subject.getsId());
        subjectHolder.tvName.setText(subject.getsName());
        subjectHolder.tvSeason.setText(subject.getSeason());
    }

    @Override
    public int getItemCount() {
        if (subjects == null) return 0;
        return subjects.size();
    }

    // 1.Tạo class SubjectHolder extends RecyclerView.ViewHolder
    class SubjectHolder extends RecyclerView.ViewHolder {

        private final TextView tvID;
        private final TextView tvName;
        private final TextView tvSeason;
        private final ImageView imgEdit;
        private final ImageView imgDelete;

        private SubjectHolder(@NonNull final View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            tvSeason = itemView.findViewById(R.id.tvSeason);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            // 7. Gán sự kiện onClick cho View
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItem(getLayoutPosition());
                }
            });

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEdit(getLayoutPosition());
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDelete(getLayoutPosition());
                }
            });

        }
    }
}

