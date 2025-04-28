package ru.mirea.zverevds.mireaproject.ui.webView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.zverevds.mireaproject.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {

    private WebView webView;
    private FragmentWebViewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WebViewViewModel webViewViewModel =
                new ViewModelProvider(this).get(WebViewViewModel.class);

        binding = FragmentWebViewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        webView = binding.webView;
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webViewViewModel.getText().observe(getViewLifecycleOwner(), url -> {webView.loadUrl(url);});

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}