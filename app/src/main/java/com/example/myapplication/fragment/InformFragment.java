package com.example.myapplication.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformFragment extends Fragment {
    TextView txtView;
    Button urlBtn;
    ImageView imgView;
    ImageView imgViewTn;

    Bitmap bitmap;
    Bitmap bitmapTn;
    String getimgUrl;

    double placeUrl1 = 0, placeUrl2 = 0;

    String key = "a4hJeRUZI4ctHoyx8WeedRazD%2FT442PIqdbpisLSyXdEsWF49VFSQLcv553T%2BTWQslP%2Be%2FBg0K94qm2yMKd7%2FA%3D%3D";

    public static InformFragment newInstance() {
        Bundle args = new Bundle();

        InformFragment fragment = new InformFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inform, container, false);

        boolean initem = false, inseq = false, intitle = false, instartDate = false, inendDate = false;
        boolean inplace = false, inrealName = false, inarea = false, inprice = false, incontents1 = false;
        boolean inurl = false, inphone = false, inimgUrl = false, inplaceUrl1 = false, inplaceUrl2 = false;
        boolean inplaceAddr = false, inplaceSeq = false;

        String seq = null, title = null, startDate = null, endDate = null, place = null;
        String realName = null, area = null, price = null, contents1 = null, isinUrl = null;
        String phone = null, imgUrl = null, placeAddr = null, placeSeq = null;


        try {
            URL url = new URL("http://www.culture.go.kr/openapi/rest/publicperformancedisplays/d/" //URL
                    + "?ServiceKey=" + key  //Service Key
                    + "&ComMsgHeader=" + "" //
                    + "&RequestTime=" + "20100801:23003422" //
                    + "&CallBackURl=" + ""//
                    + "&MsgBody=" + "" //
                    + "&seq=" + "132407"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            /*int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("seq")) {
                            inseq = true;
                        }
                        if (parser.getName().equals("title")) {
                            intitle = true;
                        }
                        if (parser.getName().equals("startDate")) {
                            instartDate = true;
                        }
                        if (parser.getName().equals("endDate")) {
                            inendDate = true;
                        }
                        if (parser.getName().equals("place")) {
                            inplace = true;
                        }
                        if (parser.getName().equals("realName")) {
                            inrealName = true;
                        }
                        if (parser.getName().equals("area")) {
                            inarea = true;
                        }
                        if (parser.getName().equals("price")) {
                            inprice = true;
                        }
                        if (parser.getName().equals("contents1")) {
                            incontents1 = true;
                        }
                        if (parser.getName().equals("url")) {
                            inurl = true;
                        }
                        if (parser.getName().equals("phone")) {
                            inphone = true;
                        }
                        if (parser.getName().equals("imgUrl")) {
                            inimgUrl = true;
                        }
                        if (parser.getName().equals("gpsX")) {
                            inplaceUrl1 = true;
                        }
                        if (parser.getName().equals("gpsY")) {
                            inplaceUrl2 = true;
                        }
                        if (parser.getName().equals("placeAddr")) {
                            inplaceAddr = true;
                        }
                        if (parser.getName().equals("placeSeq")) {
                            inplaceSeq = true;
                        }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            txtView.setText(txtView.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inseq) { //isTitle이 true일 때 태그의 내용을 저장.
                            seq = parser.getText();
                            inseq = false;
                        }
                        if (intitle) { //isAddress이 true일 때 태그의 내용을 저장.
                            title = parser.getText();
                            intitle = false;
                        }
                        if (instartDate) { //isMapx이 true일 때 태그의 내용을 저장.
                            startDate = parser.getText();
                            instartDate = false;
                        }
                        if (inendDate) { //isMapy이 true일 때 태그의 내용을 저장.
                            endDate = parser.getText();
                            inendDate = false;
                        }
                        if (inplace) { //isMapy이 true일 때 태그의 내용을 저장.
                            place = parser.getText();
                            inplace = false;
                        }
                        if (inrealName) { //isMapy이 true일 때 태그의 내용을 저장.
                            realName = parser.getText();
                            inrealName = false;
                        }
                        if (inarea) { //isMapy이 true일 때 태그의 내용을 저장.
                            area = parser.getText();
                            inarea = false;
                        }
                        if (inprice) { //isMapy이 true일 때 태그의 내용을 저장.
                            price = parser.getText();
                            inprice = false;
                        }
                        if (incontents1) { //isMapy이 true일 때 태그의 내용을 저장.
                            contents1 = parser.getText();
                            incontents1 = false;
                        }
                        if (inurl) { //isMapy이 true일 때 태그의 내용을 저장.
                            isinUrl = parser.getText();
                            inurl = false;
                        }
                        if (inphone) { //isMapy이 true일 때 태그의 내용을 저장.
                            phone = parser.getText();
                            inphone = false;
                        }
                        if (inimgUrl) { //isMapy이 true일 때 태그의 내용을 저장.
                            imgUrl = parser.getText();
                            getimgUrl = imgUrl;
                            inimgUrl = false;
                        }
                        if (inplaceUrl1) { //isMapy이 true일 때 태그의 내용을 저장.
                            placeUrl1 = Double.parseDouble(parser.getText());
                            Log.i("gps", ""+placeUrl1);
                            inplaceUrl1 = false;
                        }
                        if (inplaceUrl2) { //isMapy이 true일 때 태그의 내용을 저장.
                            placeUrl2 = Double.parseDouble(parser.getText());
                            Log.i("gps", ""+placeUrl2);
                            inplaceUrl2 = false;
                        }
                        if (inplaceAddr) { //isMapy이 true일 때 태그의 내용을 저장.
                            placeAddr = parser.getText();
                            inplaceAddr = false;
                        }
                        if (inplaceSeq) { //isMapy이 true일 때 태그의 내용을 저장.
                            placeSeq = parser.getText();
                            inplaceSeq = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("msgBody")) {
                            txtView.setText(txtView.getText()
                                    + "식별번호 : " + seq
                                    + "\n 제목: " + title
                                    + "\n 시작 : " + startDate
                                    + "\n 끝 : " + endDate
                                    + "\n 장소 : " + place
                                    + "\n 분류 : " + realName
                                    + "\n 지역 : " + area
                                    + "\n 가격 : " + price
                                    + "\n 내용 : " + contents1
                                    + "\n 전화번호 : " + phone
                                    + "\n 주소 : " + placeAddr
                                    + "\n 주소 식별번호 : " + placeSeq
                                    + "\n");

                            //urlTxtView.setText("티켓 주소 : " + isinUrl);

                            URL webimgurl = new URL(getimgUrl);

                            HttpURLConnection conn = (HttpURLConnection) webimgurl.openConnection();
                            conn.setDoInput(true);
                            conn.connect();

                            InputStream is = conn.getInputStream();
                            bitmap = BitmapFactory.decodeStream(is);

                            int height = bitmap.getHeight();
                            int width = bitmap.getWidth();

                            double hwr = 0;
                            if(height > width) {
                                hwr = (double)height / (double)width;
                            } else {
                                hwr = (double)width / (double)height;
                            }

                            double heightR = hwr * 67;

                            bitmapTn = Bitmap.createScaledBitmap(bitmap, 67, (int)heightR, true);

                            imgView.setImageBitmap(bitmap);
                            imgViewTn.setImageBitmap(bitmapTn);

                            //mapFragment.getMapAsync(this);

                            final String finalIsinUrl = isinUrl;
                            urlBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalIsinUrl));
                                    startActivity(intent);
                                }
                            });

                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }*/
        } catch (Exception e) {
            txtView.setText("에러가..났습니다...");
        }

        return view;
    }

}
