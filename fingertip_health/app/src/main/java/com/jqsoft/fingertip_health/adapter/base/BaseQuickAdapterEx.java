package com.jqsoft.fingertip_health.adapter.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017-06-06.
 */

public abstract class BaseQuickAdapterEx<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public BaseQuickAdapterEx(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public BaseQuickAdapterEx(List data) {
        super(data);
    }

    public BaseQuickAdapterEx(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public int getItemViewType(int position) {
        if (getEmptyViewCount() == 1) {
            boolean header = false && getHeaderLayoutCount() != 0;
//            boolean header = mHeadAndEmptyEnable && getHeaderLayoutCount() != 0;
            switch (position) {
                case 0:
                    if (header) {
                        return HEADER_VIEW;
                    } else {
                        return EMPTY_VIEW;
                    }
                case 1:
                    if (header) {
                        return EMPTY_VIEW;
                    } else {
                        return FOOTER_VIEW;
                    }
                case 2:
                    return FOOTER_VIEW;
                default:
                    return EMPTY_VIEW;
            }
        }
//        autoLoadMore(position);
        int numHeaders = getHeaderLayoutCount();
        if (position < numHeaders) {
            return HEADER_VIEW;
        } else {
            int adjPosition = position - numHeaders;
            int adapterCount = mData.size();
            if (adjPosition < adapterCount) {
                return getDefItemViewType(adjPosition);
            } else {
                adjPosition = adjPosition - adapterCount;
                int numFooters = getFooterLayoutCount();
                if (adjPosition < numFooters) {
                    return FOOTER_VIEW;
                } else {
                    return LOADING_VIEW;
                }
            }
        }
    }

//    public int getLoadMoreViewCount() {
//        return 0;
//    }

//    public void loadMoreComplete() {
//        super.loadMoreComplete();
//
//    }

//    public boolean isLoading() {
//        return true;
//    }


//    private void autoLoadMore(int position) {
//    }


//    @Override
//    public void onBindViewHolder(K holder, int positions) {
////        //Add up fetch logic, almost like load more, but simpler.
////        autoUpFetch(positions);
////        //Do not move position, need to change before LoadMoreView binding
////        autoLoadMore(positions);
//        int viewType = holder.getItemViewType();
//
//        switch (viewType) {
//            case 0:
//
//                convert(holder, mData.get(holder.getLayoutPosition() - getHeaderLayoutCount()));
//                break;
//            case LOADING_VIEW:
////                mLoadMoreView.convert(holder);
//                break;
//            case HEADER_VIEW:
//                break;
//            case EMPTY_VIEW:
//                break;
//            case FOOTER_VIEW:
//                break;
//            default:
//                convert(holder, mData.get(holder.getLayoutPosition() - getHeaderLayoutCount()));
//                break;
//        }
//    }


}
