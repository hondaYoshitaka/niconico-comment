module.exports = function(grunt) {
  grunt.initConfig({
  	watch: {
		styles: {
	        files: ['./web/src/main/webapp/less/*.less'],
	        tasks: ['less:development'],
	        options: {
	          nospawn: true
	        }
		}
    },
	less: {
      development: {
        options: {
          compress: true,
          yuicompress: true,
          optimization: 2
        },
        files: {
          "./web/src/main/webapp/css/less-generated/cl.css": "./web/src/main/webapp/less/cl.less"
        }
      }
    }
  });
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-contrib-watch');

  grunt.registerTask('default', ['watch']);
};