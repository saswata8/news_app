import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { News } from '../../news';

@Component({
  selector: 'app-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.css']
})
export class NewsCardComponent implements OnInit {

  @Input()
  news : News;
  
  @Input()
  useFavouriteNewsApi : boolean;

  @Output()
  addNews = new EventEmitter();

  @Output()
  deleteNews = new EventEmitter();

  constructor() {
    
   }

  ngOnInit() {

  }

  addToFavouriteList()
  {
    this.addNews.emit(this.news);
  }

  deleteFromFavouriteList()
  {
    // console.log(this.movie.title);
    this.deleteNews.emit(this.news);
  }

}
