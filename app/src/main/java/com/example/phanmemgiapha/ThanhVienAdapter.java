package com.example.phanmemgiapha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    private List<ThanhVien> thanhVienList;

    public ThanhVienAdapter(List<ThanhVien> thanhVienList) {
        this.thanhVienList = thanhVienList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanh_vien, parent, false);
        // Đặt chiều rộng của mỗi item là 1/3 chiều rộng màn hình
        int screenWidth = parent.getContext().getResources().getDisplayMetrics().widthPixels;
        view.setLayoutParams(new ViewGroup.LayoutParams(screenWidth / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ThanhVien thanhVien = thanhVienList.get(position);
        holder.tvTen.setText(thanhVien.getTen());

        // Xử lý hiển thị đường lối
        if (position == thanhVienList.size() - 1) {
            // Nếu là phần tử cuối cùng, ẩn đường ngang
            holder.viewHorizontalLine.setVisibility(View.GONE);
        } else {
            holder.viewHorizontalLine.setVisibility(View.VISIBLE);
        }

        // Điều chỉnh độ dài của đường dọc
        ViewGroup.LayoutParams params = holder.viewLine.getLayoutParams();
        params.height = 100 + (thanhVien.getTheHe() * 30); // Điều chỉnh độ dài tùy theo thế hệ
        holder.viewLine.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return thanhVienList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen;
        View viewLine, viewHorizontalLine;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            viewLine = itemView.findViewById(R.id.viewLine);
            viewHorizontalLine = itemView.findViewById(R.id.viewHorizontalLine);
        }
    }
}
