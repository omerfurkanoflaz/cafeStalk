package com.example.cafestalk.ui.order;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.cafestalk.MainActivity;
import com.example.cafestalk.MenuDrawableActivity;
import com.example.cafestalk.R;
import com.example.cafestalk.ui.home.HomeFragment;
import com.example.cafestalk.ui.orderlist.OrderListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

public class OrderFragment extends Fragment {

    public CodeScanner mCodeScanner;
    ObjectAnimator animator;
    View scannerLayout;
    View scannerBar;
    private int CAMERA_PERMISSION_CODE = 1;
    boolean check_camera = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        final CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(getContext(), scannerView);
        scannerLayout = root.findViewById(R.id.scanner_view);
        scannerBar = root.findViewById(R.id.scannerBar);
        animator = null;
        Window window = MainActivity.mainactivity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        ViewTreeObserver vto = scannerLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scannerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    scannerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    scannerLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
//***scanner bar set width begin
                ViewGroup.LayoutParams layoutParams = scannerBar.getLayoutParams();
                layoutParams.width = scannerView.getWidth() - 80;
                scannerBar.setLayoutParams(layoutParams);
                //***scanner bar set width end
                float y = (float) (scannerView.getWidth() - 180);
                float destination = (float) (scannerView.getY() + (scannerView.getHeight()) / 3);
                animator = ObjectAnimator.ofFloat(scannerBar, "translationY", scannerView.getY(), y);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(3000);
                animator.start();

            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.mainactivity,
                R.style.AppTheme_Progress_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Bekleyiniz...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 500);

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                MainActivity.mainactivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.mainactivity, result.getText(), Toast.LENGTH_SHORT).show();
                        if (result != null) {
                            MenuDrawableActivity.menuDrawableActivity.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_main, new OrderListFragment(), "fragment_order_list")
                                    .addToBackStack("fragment_order_list").commit();
                        }
                        animator.pause();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
                animator.start();
            }
        });
        Bundle bundle = MainActivity.mainactivity.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(MainActivity.mainactivity.getApplicationContext(),
                        "data:" + bundle.getString("some"),
                        Toast.LENGTH_LONG).show();
            }
        }


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("OrderFragment", "onactivity created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("OrderFragment", "on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("OrderFragment", "onresume");
        if (ActivityCompat.checkSelfPermission(MainActivity.mainactivity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            mCodeScanner.startPreview();
        } else {
            if (check_camera == false)
                requestCameraPermission();
        }
/*        ActionBar actionBar = MainActivity.mainactivity.getActionBar();
        actionBar.hide();*/
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("OrderFragment", "onpause");
        mCodeScanner.releaseResources();

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("OrderFragment", "onstop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("OrderFragment", "ondestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("OrderFragment", "ondestroy view");
    }


    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(MainActivity.mainactivity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.mainactivity, "İzin Verildi", Toast.LENGTH_LONG).show();
                mCodeScanner.startPreview();
            } else {
                check_camera = true;
                progressDialog();
                Intent i = new Intent(MainActivity.mainactivity.getApplicationContext(), MainActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.mainactivity, "İzin Verilmedi", Toast.LENGTH_LONG).show();
                Log.d("red", "izin verilmedi");


            }
        }
    }

    private void progressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.mainactivity,
                R.style.AppTheme_Progress_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Bekleyiniz...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 500);

    }
}
