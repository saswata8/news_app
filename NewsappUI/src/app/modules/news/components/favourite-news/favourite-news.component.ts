import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../news.service';
import { News } from '../../news';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-favourite-news',
  templateUrl: './favourite-news.component.html',
  styleUrls: ['./favourite-news.component.css']
})
export class FavouriteNewsComponent implements OnInit {

  newsList : Array<News>;
  useFavouriteNewsApi = true;
  
  constructor(private newsService: NewsService, private snackBar : MatSnackBar) {
    this.newsList = [];
   }

  ngOnInit() {
    this.newsService.getFavouriteNewsList().subscribe((response)=>{
      console.log(response);
      if(response.length === 0)
      {
        this.snackBar.open('Watchlist is empty', '', {
          duration : 2000
        });
      }
      this.newsList = response;
    })
  }

}
