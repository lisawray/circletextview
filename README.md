# circletextview

*note: This DOES NOT WORK with the final version of API 23!  `setIndents()` has been moved to `StaticLayout.Builder`. Hopefully soon I'll have time to demo something similar with `Layout` drawing on `Canvas`.*

A circular TextView for the Android M preview.

<img src="https://github.com/lisawray/circletextview/blob/master/screenshot.png?raw=true"/>

I didn't do anything funny to it, you can use it just like normal:
```
    <com.lisawray.circletextview.CircleTextView
        android:text="@string/hello_world"
        android:ellipsize="end"
        android:id="@+id/circle_text_view"
        android:layout_width="match_parent"
        android:textSize="12sp"
        android:gravity="center"
        android:layout_height="wrap_content"/>
```
