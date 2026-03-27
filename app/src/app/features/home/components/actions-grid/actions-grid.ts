import { Component } from '@angular/core';
import { ActionButton } from '../action-button/action-button';

@Component({
  selector: 'app-actions-grid',
  templateUrl: './actions-grid.html',
  styleUrl: './actions-grid.css',
  standalone: true,
  imports: [ActionButton],
})
export class ActionsGrid {
  readonly actions = [
    { icon: 'pix', label: 'Pix' },
    { icon: 'transfer', label: 'Transferir' },
    { icon: 'pay', label: 'Pagar' },
    { icon: 'statement', label: 'Extrato' },
    { icon: 'card', label: 'Cartões' },
    { icon: 'loan', label: 'Empréstimo' },
    { icon: 'invest', label: 'Investir' },
    { icon: 'more', label: 'Mais' },
  ];
}
