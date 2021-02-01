package com.it.m_decorator;

import java.io.*;

public class MyBufferReader extends Reader {

    private Reader in;

    public MyBufferReader(Reader in) {
        this.in = in;
    }

    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int i;
        while (true) {
            i = in.read();
            if (i == '\r') {
                continue;
            }
            if (i == '\n'||i == -1) {
                break;
            }
            sb.append((char) i);
        }
        if (sb.toString().length() == 0) {
            if (i == '\n') {
                return "";
            }
            if (i == -1) {
                return null;
            }
        }
        return sb.toString();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}

class Test{
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("E:\\1.txt");
        //BufferedReader reader = new BufferedReader(in);
        MyBufferReader reader = new MyBufferReader(in);

        String lineStr;
        while ((lineStr = reader.readLine()) != null) {
            System.out.println(lineStr);
        }

    }
}
