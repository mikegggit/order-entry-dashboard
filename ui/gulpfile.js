"use strict"

var gulp = require('gulp');
var browserify = require('browserify'); // Bundles JS
//var reactify = require('reactify'); // Transforms React JSX to JS
var concat = require('gulp-concat'); // Concatenates files
var source = require('vinyl-source-stream'); // Use conventional text streams with Gulp
var connect = require('gulp-connect'); // Runs a local dev server
var open = require('gulp-open'); // Opens a url in a browser
var babelify = require('babelify');

var config = {
  port: 3000,
  devBaseUrl: 'http://localhost',
  paths: {
    html: './src/*.html',
    js: './src/**/*.js',
    images: './src/images/*',
    css: [
//      'node_modules/bootstrap/dist/css/bootstrap.min.css',
//      'node_modules/bootstrap/dist/css/bootstrap-theme.min.css',
      'node_modules/fixed-data-table-2/dist/fixed-data-table-base.css',
      'node_modules/fixed-data-table-2/dist/fixed-data-table.css',
      'node_modules/fixed-data-table-2/dist/fixed-data-table-style.css',
      './src/css/mike.css'
    ],
    dist: './dist',
    main: './src/main.js'
  }
}

// Start local dev server
gulp.task('connect', function() {
  connect.server({
    root: ['dist'],
    port: config.port,
    base: config.devBaseUrl,
    livereload: true
  });
});

gulp.task('open', ['connect'], function() {
  gulp.src('dist/index.html')
    .pipe(open({ uri: config.devBaseUrl + ':' + config.port + '/'}));
});

gulp.task('html', function() {
  gulp.src(config.paths.html)
    .pipe(gulp.dest(config.paths.dist));
})

/*
gulp.task('test', function() {
  gulp.src(config.paths.js)
    .pipe(babel(
      {
        presets: ['es2016', 'react']
      }))
    .on('error', console.error.bind(console))
    .pipe(concat('app.js'))
    .pipe(gulp.dest(config.paths.dist + '/scripts'));
});
*/
/*gulp.task('js', function() {
  browserify(config.paths.main)
    .pipe(babel({
      presets: ['es2015']
    }))
    .bundle() // stuff js into a single file named bundle.js
    .on('error', console.error.bind(console))
    .pipe(('bundle.js'))
    .pipe(gulp.dest(config.paths.dist + '/scripts'));
});
*/
gulp.task('js', function() {
  browserify({
    entries: config.paths.main
    //debug: true,
  })
    
    .transform("babelify", {"presets": [
        "react",
        "es2015"
      ],
      "plugins": [
        "transform-object-rest-spread"
      ]
    })
    .bundle() // stuff js into a single file named bundle.js
    .on('error', console.error.bind(console))
    .pipe(source('bundle.js'))
    .pipe(gulp.dest(config.paths.dist + '/scripts'));
});
gulp.task('css', function() {
  gulp.src(config.paths.css)
    .pipe(concat('bundle.css'))
    .pipe(gulp.dest(config.paths.dist + '/css'));
});

// Migrates images to dist folder
// I could even optimize my images here
gulp.task('images', function() {
  gulp.src(config.paths.images)
    .pipe(gulp.dest(config.paths.dist + '/images'));

  //publish favicon
  gulp.src('./src/favicon.ico')
    .pipe(gulp.dest(config.paths.dist));
});

gulp.task('watch', function() {
  gulp.watch(config.paths.html, ['html']);
  gulp.watch(config.paths.js, ['js']);
});

gulp.task('default', ['html', 'js', 'css', 'images', 'open', 'watch']);
//gulp.task('default', ['test2' ]);
