import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContainerComponent } from './components/container/container.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule,MatInputModule, MatIconModule } from '@angular/material';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { InterceptorService } from './interceptor.service';
import { AuthenticationModule } from '../authentication/authentication.module';
import { NewsCardComponent } from './components/news-card/news-card.component';
import { NewsContainerComponent } from './components/news-container/news-container.component';
import { FavouriteNewsComponent } from './components/favourite-news/favourite-news.component';
import { NewsRouterModule } from './news-router.module';
import { NewsService } from './news.service';

@NgModule({
  declarations: [NewsCardComponent, ContainerComponent, NewsContainerComponent, FavouriteNewsComponent, SearchComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    NewsRouterModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatIconModule,
    FormsModule,
    MatInputModule,
    AuthenticationModule
  ],
  exports:[
    NewsCardComponent,
    NewsRouterModule
  ],
  providers:[
    NewsService, {
      provide : HTTP_INTERCEPTORS,
      useClass :  InterceptorService,
      multi : true
    }
  ]
})
export class NewsModule { }