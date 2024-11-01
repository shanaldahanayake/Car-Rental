import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [HttpClientModule],  // Import HttpClientModule here
  exports: [HttpClientModule]    // Export it to make available for other modules
})
export class CoreModule {}
