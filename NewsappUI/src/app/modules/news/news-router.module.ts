import { NgModule } from '@angular/core'
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './components/search/search.component';
import { AuthGuardService } from 'src/app/auth-guard.service';
import { NewsContainerComponent } from './components/news-container/news-container.component';
import { FavouriteNewsComponent } from './components/favourite-news/favourite-news.component';

const newsRoutes: Routes = [
    {
        path : 'news',
        children : [
            {
                path : '',
                redirectTo : '/news/popular',
                pathMatch : 'full',
                canActivate : [AuthGuardService]
            },
            {
                path : 'popular',
                component : NewsContainerComponent,
                canActivate : [AuthGuardService],
            },
            {
                path : 'favourites',
                canActivate : [AuthGuardService],
                component : FavouriteNewsComponent
            },
            {
                path : 'search',
                canActivate : [AuthGuardService],
                component : SearchComponent
            }
        ]
    }
];

@NgModule({
    imports:[
        RouterModule.forChild(newsRoutes)
    ],
    exports: [
        RouterModule
    ]
})

export class NewsRouterModule
{

}