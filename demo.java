import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Import HttpClient for making HTTP requests

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  selectedDate: Date | null = null;
  selectedNumber: number | null = null;

  constructor(private http: HttpClient) {}

  submitData() {
    const formattedDate = this.formatDate(this.selectedDate); // format the date to yyyy-MM-dd
    const requestBody = {
      date: formattedDate,
      number: this.selectedNumber
    };

    this.http.post('http://localhost:3000/api/query', requestBody)
      .subscribe(response => {
        console.log('Query result:', response);
        alert(`Query Result: ${JSON.stringify(response)}`);
      }, error => {
        console.error('Error occurred:', error);
      });
  }

  formatDate(date: Date | null): string | null {
    if (!date) return null;
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }
}
