package com.example.myapplication.ui.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.model.TestModel;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Fragment_test extends Fragment {

    View view;
    PDFView pdfViewer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_exam,container,false);
        TestModel testModel = (TestModel) getActivity().getIntent().getSerializableExtra("TEST");
        pdfViewer = view.findViewById(R.id.pdfViewer);
        if(testModel.getLinktest().length()>0){
            new RetrievePDFfromUrl(pdfViewer).execute(testModel.getLinktest());
        }
        return  view;
    }
}
class RetrievePDFfromUrl extends AsyncTask<String, Void, InputStream> {
    PDFView pdfView;
    public  RetrievePDFfromUrl(PDFView pdfView){
        this.pdfView =pdfView;
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        // we are using inputstream
        // for getting out PDF.
        InputStream inputStream = null;
        try {
            URL url = new URL(strings[0]);
            // below is the step where we are
            // creating our connection.
            HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                // response is success.
                // we are getting input stream from url
                // and storing it in our variable.
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            // this is the method
            // to handle errors.
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        // after the execution of our async
        // task we are loading our pdf in our pdf view.
        pdfView.fromStream(inputStream).load();
    }
}