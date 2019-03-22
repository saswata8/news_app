import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { News } from './news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  api_key = 'd7578a2f832c4a82ab1c325ca0e58ee2';
  springEndpoint : string;

  constructor(private http: HttpClient) {
    this.springEndpoint = 'http://localhost:8082/api/news';
  }

  initSources() {
     return this.http.get('https://newsapi.org/v2/sources?language=en&apiKey=' + this.api_key);
  }

  initArticles() {
     return this.http.get('https://newsapi.org/v2/top-headlines?country=us&apiKey=' + this.api_key);
  }

  getArticlesByKeyword(source: string) {
     return this.http.get('https://newsapi.org/v2/everything?' +
        'q=' + source +
        '&from=2019-02-25&' +
        'sortBy=popularity&apiKey=' +
        this.api_key);
  }

  addToFavouriteNewsList(news)
   {
      return this.http.post(this.springEndpoint, news);
   }

   getFavouriteNewsList()
   {
      return this.http.get<Array<News>>(this.springEndpoint);
   }

   deleteFromFavouriteList(news)
   {
      return this.http.delete(this.springEndpoint + "/" + news.id,{responseType:'text'});
   }
}
