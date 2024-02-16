import { NativeDateAdapter } from '@angular/material/core';

export class CustomDateAdapter extends NativeDateAdapter {
  format(date: Date, displayFormat: Object): string {
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    
    return this._to2digit(year) + '-' + this._to2digit(month) + '-' + this._to2digit(day);
  }

  private _to2digit(n: number) {
	if(n <= 9) 
	return ('0' + n);

	return n;
  } 
}