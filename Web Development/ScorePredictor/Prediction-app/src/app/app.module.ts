import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ChangeDetectorRef } from '@angular/core';
import { NgbModule, NgbDropdown, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    NgbModule
  ],
  providers: [NgbModule, NgbDropdown, NgbDropdownModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
