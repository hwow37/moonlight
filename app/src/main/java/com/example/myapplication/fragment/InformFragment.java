package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.myapplication.R;
import com.example.myapplication.activity.InformActivity;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformFragment extends Fragment {//implements OnMapReadyCallback {
    private String key = "a4hJeRUZI4ctHoyx8WeedRazD%2FT442PIqdbpisLSyXdEsWF49VFSQLcv553T%2BTWQslP%2Be%2FBg0K94qm2yMKd7%2FA%3D%3D";

    private FragToActivityListener fragToActivityListener;
    //private MapView mapView;
    //private GoogleMap googleMap;
    private MapView kakaoMapView;

    TextView txtView;
    TextView txtView2;
    Button urlBtn;
    ImageView imgView;
    SubsamplingScaleImageView imgInfo;
    Bitmap bitmapwep;
    Bitmap bitmapinfo;
    String getimgUrl;

    double placeUrl1 = 0, placeUrl2 = 0;

    public static InformFragment newInstance() {
        Bundle args = new Bundle();

        InformFragment fragment = new InformFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragToActivityListener) {
            fragToActivityListener = (FragToActivityListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement FragToActivityListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inform, container, false);

        //kakaoMapView = new MapView(getActivity());

        txtView = (TextView) view.findViewById(R.id.testApiText);
        txtView2 = (TextView) view.findViewById(R.id.APItestText);
        imgView = (ImageView) view.findViewById(R.id.APItestImg);
        imgInfo = (SubsamplingScaleImageView) view.findViewById(R.id.APIInfoImg);
        urlBtn = (Button) view.findViewById(R.id.urlTxtBtn);

        /*// kakao Map setting
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map);
        mapViewContainer.addView(kakaoMapView);
        //kakaoMapView.setMapViewEventListener(this); // this에 MapView.MapViewEventListener 구현.
        //kakaoMapView.setPOIItemEventListener(this);

        // 중심점 변경
        kakaoMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

        // 줌 레벨 변경
        kakaoMapView.setZoomLevel(7, true);

        // 중심점 변경 + 줌 레벨 변경
        kakaoMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);

        // 줌 인
        kakaoMapView.zoomIn(true);

        // 줌 아웃
        kakaoMapView.zoomOut(true);
*/

        //mapView = (MapView) view.findViewById(R.id.map);
        //mapView.onCreate(savedInstanceState);
        //mapView.getMapAsync(this);
        Thread seturl = new Thread() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parUrl();
                    }
                });
            }
        };
        seturl.start();

        return view;
    }

    /*@Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragToActivityListener = null;
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }


    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }


    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
    }*/

    // fragment 에서 Activity로 데이터 전달
    public interface FragToActivityListener {
        void SetDataFtoA(String infoTitle, String infoSdate, String infoEdate, String infoPlace, String imgUrl);
    }


    //  api로 받아온 값들을 파싱한다.
    public void parUrl() {

        boolean initem = false, inseq = false, intitle = false, instartDate = false, inendDate = false;
        boolean inplace = false, inrealName = false, inarea = false, inprice = false, incontents1 = false;
        boolean inurl = false, inphone = false, inimgUrl = false, inplaceUrl1 = false, inplaceUrl2 = false;
        boolean inplaceAddr = false, inplaceSeq = false;

        String seq = null, title = null, startDate = null, endDate = null, place = null;
        String realName = null, area = null, price = null, contents1 = null, isinUrl = null;
        String phone = null, imgUrl = null, placeAddr = null, placeSeq = null;

        StrictMode.enableDefaults();

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

            int parserEvent = parser.getEventType();
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
                            Log.i("gps", "" + placeUrl1);
                            inplaceUrl1 = false;
                        }
                        if (inplaceUrl2) { //isMapy이 true일 때 태그의 내용을 저장.
                            placeUrl2 = Double.parseDouble(parser.getText());
                            Log.i("gps", "" + placeUrl2);
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
                            txtView2.setText(txtView2.getText()
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

                            fragToActivityListener.SetDataFtoA(title, startDate, endDate, place, getimgUrl);

                            URL webimgurl = new URL(getimgUrl);
                            URL infoimgurl = new URL(parContents(contents1));

                            HttpURLConnection conn1 = (HttpURLConnection) webimgurl.openConnection();
                            HttpURLConnection conn2 = (HttpURLConnection) infoimgurl.openConnection();
                            conn1.setDoInput(true);
                            conn2.setDoInput(true);
                            conn1.connect();
                            conn2.connect();

                            InputStream is1 = conn1.getInputStream();
                            InputStream is2 = conn2.getInputStream();
                            bitmapwep = BitmapFactory.decodeStream(is1);
                            bitmapinfo = BitmapFactory.decodeStream(is2);

                            imgView.setImageBitmap(bitmapwep);
                            imgInfo.setImage(ImageSource.bitmap(bitmapinfo));

                            //LatLng placeGps = new LatLng(placeUrl2, placeUrl1);
                            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(placeGps));
                            //mapView.getMapAsync(this);
                            //mapView.getMapAsync(this);

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
            }
        } catch (Exception e) {
            //txtView.setText("에러가..났습니다...");
            Log.i("onUrl", "into catch");
        }

    }

    /*@Override
    public void onMapReady(final GoogleMap map) {
        LatLng placeLat = new LatLng(127.004, 37.580);//,placeUrl2, placeUrl1);

        googleMap = map;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(placeLat);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(placeLat));
        Log.i("GOOGLE_MAP", "onMapReady");
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }*/

    public String parContents(String str) {
        String[] temp1;
        String[] temp2;
        temp1 = str.split("src=\"");
        temp2 = temp1[1].split("\"");
        System.out.println(temp2[0]);

        return temp2[0];
    }
}
