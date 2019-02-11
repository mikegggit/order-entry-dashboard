UI
==

Building the ui
---------------
Gulp + Browserify is used to implement the build pipeline.  

### Lessons learned
Based on limited experience, I prefer Webpack to Gulp + Browserify.

* Gulp + Browserify feel less integrated into the development process than Webpack
* Webpack plugins and configuration makes more sense to me.
* Npm scripts are native, gulp tasks aren't.
* CSS modules and resource bundling seem to work better with Webpack.


To build the UI...
```console
Michaels-Air:ui grudkowm$ gulp
[18:43:48] Using gulpfile /private/var/tmp/order-entry-dashboard/ui/gulpfile.js
[18:43:48] Starting 'html'...
[18:43:48] Finished 'html' after 5.95 ms
[18:43:48] Starting 'js'...
[18:43:48] Finished 'js' after 7.15 ms
[18:43:48] Starting 'css'...
[18:43:48] Finished 'css' after 3.17 ms
[18:43:48] Starting 'images'...
[18:43:48] Finished 'images' after 1.33 ms
[18:43:48] Starting 'connect'...
[18:43:48] Starting server...
[18:43:48] Finished 'connect' after 15 ms
[18:43:48] Starting 'open'...
[18:43:48] Finished 'open' after 531 μs
[18:43:48] Starting 'watch'...
[18:43:48] Finished 'watch' after 23 ms
[18:43:48] Starting 'default'...
[18:43:48] Finished 'default' after 65 μs
[18:43:48] Server started http://localhost:3000
[18:43:48] LiveReload started on port 35729
[18:43:48] Running server
```

