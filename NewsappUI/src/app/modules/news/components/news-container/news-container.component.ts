import { Component, OnInit } from '@angular/core';
import { News } from '../../news';
import { NewsService } from '../../news.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-news-container',
  templateUrl: './news-container.component.html',
  styleUrls: ['./news-container.component.css']
})
export class NewsContainerComponent implements OnInit {

  newsList : Array<any>;
  // movieType: string;
  
  constructor(private newsService: NewsService, private route: ActivatedRoute) {
    this.newsList = [];
    // this.route.data.subscribe(data=>{
    //   this.movieType = data.movieType;
    // })
   }

  ngOnInit() {
    this.newsService.initArticles().subscribe((response)=>{
      this.newsList = response['articles'];
    });
  }
  

}
