package com.baseproject.ui.main;

import com.baseproject.data.models.PostModel;
import com.baseproject.data.server.ForumService;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter{

    private MainView mainView;
    private ForumService mForum;

    public MainPresenterImpl(MainView mainView, ForumService mForum) {
        this.mainView = mainView;
        this.mForum=mForum;
    }

    @Override public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
        loadPosts();
    }

    @Override public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override public void onDestroy() {
        mainView = null;
    }


    public MainView getMainView() {
        return mainView;
    }

    public void loadPosts() {

        mForum.getApi()
            .getPosts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<PostModel>>() {
                @Override
                public void onCompleted() {
                  mainView.hideProgress();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(List<PostModel> posts) {
                    mainView.setItems(posts);
                }
            });
    }
}
