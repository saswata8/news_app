import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../news.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  newsList: Array<any>;

  constructor(private newsService: NewsService) { }

  ngOnInit() {
  }

  onEnter(searchKey) {
    this.newsService.getArticlesByKeyword(searchKey).subscribe(response => {
      this.newsList = response['articles'];
    })
  }

}
