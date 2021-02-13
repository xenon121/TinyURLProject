# TinyURLProject

in this project there are total 3 api.
1. create api : this will take input url in query param and will generate new tiny url, in response it will show tiny url link.
  ex:- http://localhost:8080/tiny-url/create?inputUrl='url'
  
2. get api : this will return whole response (tinyurl, fullurl, accessed count) based given tinyurl.
  ex:- http://localhost:8080/tiny-url/get/{'tinyurl'}
  
3. redirect full api: basically when we call tinyurl in browser then this api get call and redirect to actual url that has been used to generate tiny url.
  ex:- http://localhost:8080/tiny-url/{'tinyurl'}
