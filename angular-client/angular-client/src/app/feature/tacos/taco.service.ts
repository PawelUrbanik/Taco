import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TacoService implements OnInit{

  recentTacos: any;
  private baseUrl: string = 'http://localhost:8080/design/recent';
  constructor(private http: HttpClient) { }

  getTacos(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  ngOnInit(): void {
    this.recentTacos = this.http.get<any>(this.baseUrl).subscribe(data => this.recentTacos = data)
  }
}
