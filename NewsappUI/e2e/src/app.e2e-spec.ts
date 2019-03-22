import { AppPage } from './app.po';
import { browser, logging, element, protractor, by } from 'protractor';
describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should be redirected to /login route on opening the application', () => {
page.navigateTo();
    browser.driver.manage().window().maximize();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('user');
    browser.element(by.id('lastName')).sendKeys('Super lastUser');
    browser.element(by.id('userId')).sendKeys('user');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/');
  });


  it('should be able to login user and navigate to headlines news', () => {
    browser.element(by.id('userid')).sendKeys('user');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/news/popular');
  });



  it('should be able to add news to favouriteList', async() => {

    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.news-card'));
    expect(searchItems.isPresent()).toBeTruthy();
    searchItems.get(0).click();
    browser.element(by.id('add-button')).click();
    browser.driver.sleep(10000);
  });

it('should be able to search news and add to favorite', () => {
    browser.element(by.id('search')).click();
    expect(browser.getCurrentUrl()).toContain('/news/search');
    browser.element(by.id('search-button-input')).sendKeys('america');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.css('.news-card'));
    expect(searchItems.isPresent()).toBeTruthy();
searchItems.get(0).click();
    browser.element(by.id('add-button')).click();
    browser.driver.sleep(10000);

  });

it('should be able to navigate to favorite page and able to see fav news', ()=>{

browser.element(by.id('myFavourites')).click();
const searchItems = element.all(by.css('.news-card'));
    expect(searchItems.isPresent()).toBeTruthy();
});

it('should be able to logout and go back to login page', ()=>{
browser.element(by.id('logout')).click();
browser.driver.sleep(2000);
expect(browser.getCurrentUrl()).toContain('/login');

});

});
