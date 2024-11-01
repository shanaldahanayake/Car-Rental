import { Injectable } from '@angular/core';
import { User } from './user.model';

const TOKEN="token";
const USER="user";
@Injectable({
  providedIn: 'root'
})

export class StorageService {
  constructor() { }

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
}

static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
}

static getToken() {
    return window.localStorage.getItem(TOKEN);
}

static getUser(): User | null {
    const userData = localStorage.getItem(USER);
    return userData ? JSON.parse(userData) as User : null;
}

static getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : "";
}

static isAdminLogged(): boolean {
    if (this.getToken() == null) return false;
    const role: string = this.getUserRole();
    return role === "ADMIN";
}

static isCustomerLogged(): boolean {
    if (this.getToken() == null) return false;
    const role: string = this.getUserRole();
    return role === "CUSTOMER";
}
}
