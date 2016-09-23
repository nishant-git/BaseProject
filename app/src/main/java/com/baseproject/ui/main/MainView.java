package com.baseproject.ui.main;

import com.baseproject.data.models.PostModel;
import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<PostModel> items);

    void showMessage(String message);
}
