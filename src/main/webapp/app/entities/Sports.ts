import { SportPositions } from './SportPositions';
import { Leagues } from './Leagues';
export interface Sports {
    sportId: number;
    name: string;
    sportPositions?: SportPositions;
    leagues?: Leagues[];
}
