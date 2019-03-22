import { Component, OnInit, Input} from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { error } from 'util';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  newsList : Array<any>;

  @Input()
  useFavouriteNewsApi : boolean;

  favouriteNews : News;

  constructor(private newsService: NewsService, private snackBar : MatSnackBar) {
   
   }

  ngOnInit() {
    this.newsService.getFavouriteNewsList().subscribe(()=>{});
  }

  addToFavouriteNews(news)
  {
    this.favouriteNews = news;
    this.favouriteNews.sourceWebsiteName = news.source.name;
    this.newsService.addToFavouriteNewsList(this.favouriteNews).subscribe(()=>{
      this.snackBar.open('News added to favourite list', '', {
        duration : 2000
      });
    },
  error=>{
    this.snackBar.open(error.error, '', {
      duration : 2000
    });
  });
  }

  deleteFromFavouriteList(news)
  {
    let message = `News deleted from your favourites list`;

    for(var i = 0; i < this.newsList.length; i++)
    {
      if(this.newsList[i].id === news.id)
      {
          this.newsList.splice(i,1);
      }
    }

    this.newsService.deleteFromFavouriteList(news).subscribe(()=>{
      this.snackBar.open(message, '', {
        duration: 2000
      });
    });
  }

}
