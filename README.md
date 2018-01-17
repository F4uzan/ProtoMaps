### ProtoMaps
#### A fancy introduction to a simple web wrapper made in one hour

Today (2018/01/17), Google rolls out beta programme for Google Maps Go. It was a fine concept: packaging PWA into seemingly native apps and treating it as one.

*Except that it was not fine.*

To use Google Maps Go, you need Google Chrome/Chromium (or perhaps a fork of thus). A requirement sanely made by Google because the app itself required the "web APK" function from Chrome.

However, I was not ready to switch back to Chrome; not for a mundane app such as this. I, instead, partake the classic song and rhythm of every bored developer: let's make a fork out of this app.

#### Okay, what's the difference between this and Maps Go?

ProtoMaps does not rely on Google Chrome for its rendering, it uses your system's native WebView, eliminating the needs for Google Chrome.

ProtoMaps tries to keep the "lite" and "svelte" concept of Maps Go. Whilst there is a noticeable size differences (90 KB for Maps Go vs 1.4 MB for ProtoMaps), you should not worry much about it, as the bigger size was needed for an app that doesn't rely on Chrome.

Of course, this also is open source, you are free to fork and change this simple wrapper however and whatever you want.

#### Can I take parts of/all of the code?

If you are as bored as I am, sure.