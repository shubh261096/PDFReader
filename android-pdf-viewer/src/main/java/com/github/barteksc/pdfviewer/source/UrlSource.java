package com.github.barteksc.pdfviewer.source;

import android.content.Context;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.File;
import java.io.IOException;

public class UrlSource implements DocumentSource {
    private String url;

    public UrlSource(String url) {
        this.url = url;
    }

    @Override
    public PdfDocument createDocument(Context context, PdfiumCore core, String password) throws IOException {
        final String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDFViewCache/";
        int index = url.lastIndexOf("/");
        String fileName = url.substring(index);
        File file = new File(SDPath, fileName);
        if (file.exists()) {
            ParcelFileDescriptor pfd = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            return core.newDocument(pfd, password);
        }
        return null;

    }
}