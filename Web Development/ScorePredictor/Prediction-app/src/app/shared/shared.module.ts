import { NgbModule, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopBarComponent } from './top-bar/top-bar.component';



@NgModule({
  declarations: [
    TopBarComponent
  ],
  imports: [
    CommonModule,
    NgbModule,
    NgbDropdownModule
  ],
  exports: [
    TopBarComponent
  ],
  entryComponents: [
    TopBarComponent
  ]
})
export class SharedModule { }
